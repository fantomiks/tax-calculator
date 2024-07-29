import scala.util.control.Breaks._

object TaxCalculator {

  private case class TaxBracket(upperLimit: Double, rate: Double)

  private val taxBrackets: List[TaxBracket] = List(
    TaxBracket(23200, 0.10),
    TaxBracket(94300, 0.12),
    TaxBracket(201050, 0.22),
    TaxBracket(383900, 0.24),
    TaxBracket(487450, 0.32),
    TaxBracket(731200, 0.35),
    TaxBracket(Double.MaxValue, 0.37)
  )

  private def calculateTax(income: Double): Double = {
    var tax = 0.0
    var previousLimit = 0.0

    breakable {
      for (bracket <- taxBrackets) {
        if (income > bracket.upperLimit) {
          tax += (bracket.upperLimit - previousLimit) * bracket.rate
          previousLimit = bracket.upperLimit
        } else {
          tax += (income - previousLimit) * bracket.rate
          break()
        }
      }
    }
    tax
  }

  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      println("Usage: scala TaxCalculator <income>")
      System.exit(1)
    }

    val income = args(0).toDouble
    val tax = calculateTax(income)
    println(s"Income: $$${income}, Tax: $$${tax}")
  }
}
