import org.scalatest.funsuite.AnyFunSuite

class TaxCalculatorSpec extends AnyFunSuite {

  test("Tax calculation for Single filers with income of 50,000") {
    given List[TaxCalculator.TaxBracket] = TaxCalculator.singleFilers
    val income = 50000.0
    val (tax, maxRate) = TaxCalculator.calculateTax(income)
    val expectedTax = 6053.00 // 10% of 11600 + 12% of (47150-11600) + 22% of (50000-47150)
    assert(tax == expectedTax)
    assert(maxRate == 0.22)
  }

  test("Tax calculation for Married filing jointly with income of 100,000") {
    given List[TaxCalculator.TaxBracket] = TaxCalculator.marriedFilingJointly
    val income = 100000.0
    val (tax, maxRate) = TaxCalculator.calculateTax(income)
    val expectedTax = 12106.0 // 10% of 23200 + 12% of (94300-23200) + 22% of (100000-94300)
    assert(tax == expectedTax)
    assert(maxRate == 0.22)
  }

  test("Tax calculation for Married filing separately with income of 75,000") {
    given List[TaxCalculator.TaxBracket] = TaxCalculator.marriedFilingSeparately
    val income = 75000.0
    val (tax, maxRate) = TaxCalculator.calculateTax(income)
    val expectedTax = 11553.00 // 10% of 11600 + 12% of (47150-11600) + 22% of (75000-47150)
    assert(tax == expectedTax)
    assert(maxRate == 0.22)
  }

  test("Tax calculation for Head of household with income of 120,000") {
    given List[TaxCalculator.TaxBracket] = TaxCalculator.headOfHousehold
    val income = 120000.00
    val (tax, maxRate) = TaxCalculator.calculateTax(income)
    val expectedTax = 20149.00 // 10% of 16550 + 12% of (63100-16550) + 22% of (100500-63100) + 24% of (120000-100500)
    assert(tax == expectedTax)
    assert(maxRate == 0.24)
  }
}
