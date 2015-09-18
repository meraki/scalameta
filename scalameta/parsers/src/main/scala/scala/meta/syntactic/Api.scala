package scala.meta
package syntactic

import scala.meta.internal.parsers.ScalametaParser
import scala.meta.internal.parsers.ScalametaParser._

private[meta] trait ScalametaParseApi {
  implicit lazy val parseStat: Parse[Stat] = toParse(_.parseStat())
  implicit lazy val parseTerm: Parse[Term] = toParse(_.parseTerm())
  implicit lazy val parseTermArg: Parse[Term.Arg] = toParse(_.parseTermArg())
  implicit lazy val parseTermParam: Parse[Term.Param] = toParse(_.parseTermParam())
  implicit lazy val parseType: Parse[Type] = toParse(_.parseType())
  implicit lazy val parseTypeArg: Parse[Type.Arg] = toParse(_.parseTypeArg())
  implicit lazy val parseTypeParam: Parse[Type.Param] = toParse(_.parseTypeParam())
  implicit lazy val parsePat: Parse[Pat] = toParse(_.parsePat())
  implicit lazy val parsePatArg: Parse[Pat.Arg] = toParse(_.parsePatArg())
  implicit lazy val parsePatType: Parse[Pat.Type] = toParse(_.parsePatType())
  implicit lazy val parseCase: Parse[Case] = toParse(_.parseCase())
  implicit lazy val parseCtorCall: Parse[Ctor.Call] = toParse(_.parseCtorCall())
  implicit lazy val parseTemplate: Parse[Template] = toParse(_.parseTemplate())
  implicit lazy val parseMod: Parse[Mod] = toParse(_.parseMod())
  implicit lazy val parseEnumerator: Parse[Enumerator] = toParse(_.parseEnumerator())
  implicit lazy val parseImportee: Parse[Importee] = toParse(_.parseImportee())
  implicit lazy val parseSource: Parse[Source] = toParse(_.parseSource())
}

object parseApi extends GenericParseApi with ScalametaParseApi