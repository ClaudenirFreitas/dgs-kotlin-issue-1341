package com.freitas.dgskotlinissue1341.datafetcher

import com.freitas.dgskotlinissue1341.exception.MissingDataException
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class ShowsDataFetcher {

    private val shows = listOf(
            Show("Stranger Things", 2016),
            Show("Ozark", 2017),
            Show("The Crown", 2016),
            Show("Dead to Me", 2019),
            Show("Orange is the New Black", 2013))

    @DgsQuery
    fun shows(@InputArgument titleFilter: String?): List<Show> {
        return if (titleFilter.isNullOrBlank()) {
            throw MissingDataException()
        } else {
            shows.filter { it.title.contains(titleFilter) }
        }
    }

    data class Show(val title: String, val releaseYear: Int)

}
