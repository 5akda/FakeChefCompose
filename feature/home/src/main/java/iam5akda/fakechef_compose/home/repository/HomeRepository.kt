package iam5akda.fakechef_compose.home.repository

import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getAnimatedString(): Flow<String>
}