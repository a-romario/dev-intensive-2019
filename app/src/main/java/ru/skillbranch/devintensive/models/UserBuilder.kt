package ru.skillbranch.devintensive.models

import java.util.*

class UserBuilder {
    private var id: String = "0"
    private var firstName: String? = null
    private var lastName: String? = null
    private var avatar: String? = null
    private var rating: Int = 0
    private var respect: Int = 0
    private var lastVisit: Date? = Date()
    private var isOnline: Boolean = false

    fun id(id: String): UserBuilder {
        this.id = id
        return this
    }

    fun firstName(firstName: String): UserBuilder {
        this.firstName = firstName
        return this
    }

    fun lastName(lastName: String): UserBuilder {
        this.lastName = lastName
        return this
    }

    fun avatar(avatar: String): UserBuilder {
        this.avatar = avatar
        return this
    }

    fun rating(rating: Int): UserBuilder {
        this.rating = rating
        return this
    }

    fun respect(respect: Int): UserBuilder {
        this.respect = respect
        return this
    }

    fun lastVisit(lastVisit: Date): UserBuilder {
        this.lastVisit = lastVisit
        return this
    }

    fun isOnline(isOnline: Boolean): UserBuilder {
        this.isOnline = isOnline
        return this
    }

    fun build(): User = User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
}