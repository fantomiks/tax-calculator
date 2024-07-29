# Tax Calculator

A Scala application for calculating income tax based on different filing types. This tool computes total tax and net income using predefined tax brackets for various filing statuses.

## Features

- Supports multiple filing types:
    - Single
    - Married Filing Jointly
    - Married Filing Separately
    - Head of Household
- Calculates total tax and net income
- Displays maximum tax rate applied
- User-friendly command-line interface

## Usage

Compile and run the program using Scala. The application requires two command-line arguments:

1. **Filing Type**: Type of tax filing. Options are:
    - `single`
    - `married_jointly`
    - `married_separately`
    - `head_of_household`
2. **Income**: The income amount to calculate tax for.

### Command-Line Example

```sh
scala TaxCalculator <filingType> <income>
```

### Example

To calculate the tax for a single filer with an income of $50,000, use the following command:

```sh
scala TaxCalculator single 50000
```

This command will output the following details:

- **Filing Type**: Displays the type of tax filing used (e.g., Single).
- **Income**: Shows the income amount provided.
- **Total Tax**: The calculated total tax.
- **Net Income**: The income amount after tax deductions.
- **Gross Income**: The original income amount.
- **Maximum Tax Rate Applied**: The highest tax rate applicable to the given income.

### Example Output

For a married couple filing jointly with an income of $175,000, the output might look like this:

```sh
Filing Type: Married jointly
Income: $175000.00
Total Tax: $28606.00
Net Income: $146394.00
Gross Income: $175000.00
Maximum Tax Rate Applied: 22.00%
```

## Tax Brackets Information

The tax brackets used in this program are based on the U.S. tax brackets for 2024. For more information, please refer to [Fidelity's Tax Bracket Guide](https://www.fidelity.com/learning-center/personal-finance/tax-brackets).

## Dependencies

- Scala 3.x

## Building and Running

1. Clone the repository:
   ```sh
   git clone https://github.com/fantomiks/tax-calculator.git
   ```

2. Navigate to the project directory:
   ```sh
   cd tax-calculator
   ```

3. Compile and run the program using Scala.
