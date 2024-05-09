fun main(){
//    Use Nullable Types Wisely: Only use nullable types (Type?) when a variable can indeed be null. If a variable should not be null, use a non-nullable type.

//    Safe Calls (?.): Use the safe call operator when accessing properties or methods on a nullable object. This will return null if the object is null instead of throwing a NullPointerException.

    val nonNullableString : String
    val nullableString : String? = null
    val lengthofString = nullableString?.length //length of string will be null

//    Elvis Operator (?:): The Elvis operator lets you to offer an alternative value if an expression evaluates to null.

    val length1 = nullableString?.length ?: 0

//    Non-Null Assertion (!!): Use this operator only when you are certain that the value is not null. It will throw a NullPointerException if the value is null.

    val length = nullableString!!.length //-> this throws NPE

//    Use let for Null Checks: The let function can be used to execute block of code only if the variable is not null.

    nullableString?.let {
        // Code here will only run if nullableString is not null
    }

}