package iam5akda.fakechef_compose.game.model

data class GameLobbyData(
    val status: String = "",
    val hostId: String = "",
    val fakeChefId: String = "",
    val headChefId: String = "",
    val encodedDish: String = "",
    val players: HashMap<String, PlayerData>? = null
) {
    fun getPlayerList(): List<PlayerData> {
        return players?.let { hashMap ->
            hashMap.toList()
                .map { it.second }
                .sortedBy { it.registeredTime }
        } ?: kotlin.run {
            emptyList()
        }
    }

    fun getHostPlayer(): PlayerData {
        return getPlayerList().find {
            it.tempUserId == hostId
        } ?: PlayerData("Something went wrong!")
    }

    fun isHost(playerId: String): Boolean = playerId == hostId
}