package im.syf.geha.data.network.response

data class SearchDto(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<UserDto>,
) {
    companion object {
        val EXAMPLE: SearchDto = SearchDto(
            total_count = 12,
            incomplete_results = false,
            items = List(12) { UserDto.SEARCH_EXAMPLE },
        )
    }
}
