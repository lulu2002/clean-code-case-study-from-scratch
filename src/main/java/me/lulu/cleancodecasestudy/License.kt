package me.lulu.cleancodecasestudy

data class License(
    val user: User,
    val codecast: Codecast,
    val types: List<LicenseType>
)

enum class LicenseType {
    VIEWABLE, DOWNLOADABLE
}
