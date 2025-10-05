package mate.academy

class FinancialService {
    fun transferFunds(
        source: AccountNumber,
        destination: AccountNumber,
        amount: CurrencyAmount,
        currencyCode: CurrencyCode,
        transactionId: TransactionId
    ): String {
        return "Transferred ${amount.amount} ${currencyCode.code} from ${source.number} to ${destination.number}. " +
                "Transaction ID: ${transactionId.id}"
    }

    fun convertCurrency(
        amount: CurrencyAmount,
        fromCurrency: CurrencyCode,
        toCurrency: CurrencyCode
    ): CurrencyAmount {
        val rate = getExchangeRate(fromCurrency, toCurrency)
        return CurrencyAmount(amount.amount * rate)
    }

    private fun getExchangeRate(fromCurrency: CurrencyCode, toCurrency: CurrencyCode): Double {
        // Placeholder exchange rate - in a real application, you'd fetch this from a financial API
        return when {
            fromCurrency.code == "USD" && toCurrency.code == "EUR" -> 0.93
            fromCurrency.code == "USD" && toCurrency.code == "GBP" -> 0.82
            else -> 1.0
        }
    }
}

private const val CODE_LENGTH = 3
private const val ZERO = 0
private const val ACCOUNT_LENGTH = 10

@JvmInline
value class CurrencyCode(val code: String) {
    init {
        require(code.length == CODE_LENGTH && code.all { it.isUpperCase() }) { "Wrong currency code format: $code" }
    }
}

@JvmInline
value class CurrencyAmount(val amount: Double) {
    init {
        require(amount >= ZERO) { "Amount cannot be negative: $amount" }
    }
}

@JvmInline
value class TransactionId(val id: String) {
    init {
        require(id.isNotEmpty()) { "Wrong transaction id format: $id" }
    }
}

@JvmInline
value class AccountNumber(val number: String) {
    init {
        require(number.length == ACCOUNT_LENGTH && number.all { it.isDigit() }) { "Wrong account number format: $number" }
    }
}
