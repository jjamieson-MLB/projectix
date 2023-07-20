package com.mlb.ballpark.projectix.common.presentation.models

data class ProjecTixMatchup(

    val mAwayNameAbbrev: String? = null,

    val mAwayNameFull: String? = null,

    val mAwayNameShort: String? = null,

    val mAwayNameTeam: String? = null,

    val mAwayTeamIconRes: String? = null,

    val descr: String? = null,

    val mGameDate: String? = null,

    val mGameTimeLocalStr: String? = null,

    val mHomeNameAbbrev: String? = null,

    val mHomeNameFull: String? = null,

    val mHomeNameShort: String? = null,

    val mHomeNameTeam: String? = null,

    val mHomeTeamIconRes: String? = null,

    val mVenueCity: String? = null,

    val mVenueName: String? = null,

    var selected: Boolean = false,
)
