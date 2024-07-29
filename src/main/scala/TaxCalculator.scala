object TaxCalculator {

  def calculateTax(income: Double): Double = {
    income match {
      case _ if income <= 23200 => income * 0.10
      case _ if income <= 94300 => (23200 * 0.10) + ((income - 23200) * 0.12)
      case _ if income <= 201050 => (23200 * 0.10) + (94300 - 23200) * 0.12 + (income - 94300) * 0.22
      case _ if income <= 383900 => (23200 * 0.10) + (94300 - 23200) * 0.12 + (201050 - 94300) * 0.22 + (income - 201050) * 0.24
      case _ if income <= 487450 => (23200 * 0.10) + (94300 - 23200) * 0.12 + (201050 - 94300) * 0.22 + (383900 - 201050) * 0.24 + (income - 383900) * 0.32
      case _ if income <= 731200 => (23200 * 0.10) + (94300 - 23200) * 0.12 + (201050 - 94300) * 0.22 + (383900 - 201050) * 0.24 + (487450 - 383900) * 0.32 + (income - 487450) * 0.35
      case _ => (23200 * 0.10) + (94300 - 23200) * 0.12 + (201050 - 94300) * 0.22 + (383900 - 201050) * 0.24 + (487450 - 383900) * 0.32 + (731200 - 487450) * 0.35 + (income - 731200) * 0.37
    }
  }

  def main(args: Array[String]): Unit = {
    val income = 150000.0 // Input income here
    val tax = calculateTax(income)
    println(s"Income: $$${income}, Tax: $$${tax}")
  }
}
