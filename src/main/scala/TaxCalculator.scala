import scala.util.control.Breaks._

object TaxCalculator {

  case class TaxBracket(upperLimit: Double, rate: Double)

  val singleFilers: List[TaxBracket] = List(
    TaxBracket(11600, 0.10),
    TaxBracket(47150, 0.12),
    TaxBracket(100525, 0.22),
    TaxBracket(191950, 0.24),
    TaxBracket(243725, 0.32),
    TaxBracket(609350, 0.35),
    TaxBracket(Double.MaxValue, 0.37)
  )

  val marriedFilingJointly: List[TaxBracket] = List(
    TaxBracket(23200, 0.10),
    TaxBracket(94300, 0.12),
    TaxBracket(201050, 0.22),
    TaxBracket(383900, 0.24),
    TaxBracket(487450, 0.32),
    TaxBracket(731200, 0.35),
    TaxBracket(Double.MaxValue, 0.37)
  )

  val marriedFilingSeparately: List[TaxBracket] = List(
    TaxBracket(11600, 0.10),
    TaxBracket(47150, 0.12),
    TaxBracket(100525, 0.22),
    TaxBracket(191150, 0.24),
    TaxBracket(243725, 0.32),
    TaxBracket(365600, 0.35),
    TaxBracket(Double.MaxValue, 0.37)
  )

  val headOfHousehold: List[TaxBracket] = List(
    TaxBracket(16550, 0.10),
    TaxBracket(63100, 0.12),
    TaxBracket(100500, 0.22),
    TaxBracket(191150, 0.24),
    TaxBracket(243700, 0.32),
    TaxBracket(609350, 0.35),
    TaxBracket(Double.MaxValue, 0.37)
  )

  // Map for converting filing type to readable string
  val filingTypeNames: Map[String, String] = Map(
    "single" -> "Single",
    "married_jointly" -> "Married jointly",
    "married_separately" -> "Married separately",
    "head_of_household" -> "Head of household"
  )

  given defaultTaxBrackets: List[TaxBracket] = singleFilers

  def calculateTax(income: Double)(using taxBrackets: List[TaxBracket]): (Double, Double) = {
    var tax = 0.0
    var previousLimit = 0.0
    var maxRate = 0.0

    breakable {
      for (bracket <- taxBrackets) {
        if (income > bracket.upperLimit) {
          tax += (bracket.upperLimit - previousLimit) * bracket.rate
          previousLimit = bracket.upperLimit
        } else {
          tax += (income - previousLimit) * bracket.rate
          maxRate = bracket.rate
          break()
        }
        maxRate = bracket.rate
      }
    }
    (tax, maxRate)
  }

  def main(args: Array[String]): Unit = {
    if (args.length != 2) {
      println("Usage: scala TaxCalculator <filingType> <income>")
      println("filingType: single | married_jointly | married_separately | head_of_household")
      System.exit(1)
    }

    val filingType = args(0)
    val income = args(1).toDouble

    given taxBrackets: List[TaxBracket] = filingType match {
      case "single" => singleFilers
      case "married_jointly" => marriedFilingJointly
      case "married_separately" => marriedFilingSeparately
      case "head_of_household" => headOfHousehold
      case _ =>
        println("Invalid filing type. Use one of: single | married_jointly | married_separately | head_of_household")
        System.exit(1)
        List() // Just to satisfy the return type, this will never be reached
    }

    val (tax, maxRate) = calculateTax(income)
    val netIncome = income - tax

    println(s"Filing Type: ${filingTypeNames.getOrElse(filingType, "Unknown")}")
    println(f"Income: $$${income}%.2f")
    println(f"Total Tax: $$${tax}%.2f")
    println(f"Net Income: $$${netIncome}%.2f")
    println(f"Gross Income: $$${income}%.2f")
    println(f"Maximum Tax Rate Applied: ${maxRate * 100}%.2f%%")
  }
}
