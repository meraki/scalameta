package org.langmeta.internal.io

import java.net.URI
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors
import scalapb.GeneratedMessage
import org.langmeta.io._

object PlatformFileIO {

  def readAllBytes(uri: URI): Array[Byte] = {
    val is = uri.toURL.openStream()
    try {
      InputStreamIO.readBytes(is)
    } finally {
      is.close()
    }
  }

  def readAllBytes(path: AbsolutePath): Array[Byte] =
    Files.readAllBytes(path.toNIO)

  def write(path: AbsolutePath, proto: GeneratedMessage): Unit = {
    path.toFile.getParentFile.mkdirs()
    val os = Files.newOutputStream(path.toNIO)
    try proto.writeTo(os)
    finally os.close()
  }

  def slurp(path: AbsolutePath, charset: Charset): String =
    scala.io.Source.fromFile(path.toFile)(scala.io.Codec(charset)).mkString

  def listFiles(path: AbsolutePath): ListFiles =
    new ListFiles(path, Option(path.toFile.list()).toList.flatten.map(RelativePath.apply))

  def isFile(path: AbsolutePath): Boolean =
    Files.isRegularFile(path.toNIO)

  def isDirectory(path: AbsolutePath): Boolean =
    Files.isDirectory(path.toNIO)

  def listAllFilesRecursively(root: AbsolutePath): ListFiles = {
    import scala.collection.JavaConverters._
    val relativeFiles = Files
      .walk(root.toNIO)
      .collect(Collectors.toList[Path])
      .asScala
      .collect {
        case path if Files.isRegularFile(path) =>
          RelativePath(root.toNIO.relativize(path))
      }
    new ListFiles(root, relativeFiles.toList)
  }
}
