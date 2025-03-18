package com.example.inventorymanagementapp.data.models

interface Searchable
{
    val name : String

    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}