package com.example.homework.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

interface Item {
    @Parcelize
    data class Header(var name: String, var surname: String, var grade: String) : Item, Parcelable {
        val NAME_KEY: String = "NAME_KEY"
        val SURNAME_KEY: String = "SURNAME_KEY"
        val GRADE_KEY: String = "GRADE_KEY"
        override fun getContent(): HashMap<String, String> {
            var hashMap: HashMap<String, String> = HashMap()
            hashMap.put(NAME_KEY, name)
            hashMap.put(SURNAME_KEY, surname)
            hashMap.put(GRADE_KEY, grade)
            return hashMap
        }
    }

    @Parcelize
    data class ProjectDescription(var title: String, var description: String) : Item, Parcelable {
        val TITLE_KEY: String = "TITLE_KEY"
        val DESCRIPTION_KEY: String = "DESCRIPTION_KEY"
        override fun getContent(): HashMap<String, String> {
            var hashMap: HashMap<String, String> = HashMap()
            hashMap.put(TITLE_KEY, title)
            hashMap.put(DESCRIPTION_KEY, description)
            return hashMap
        }
    }

    @Parcelize
    data class SkillItem(var title: String, var experience: String) : Item, Parcelable {
        val SKILL_KEY: String = "SKILL_KEY"
        val EXPERIENCE_KEY: String = "EXPERIENCE_KEY"
        override fun getContent(): HashMap<String, String> {
            var hashMap: HashMap<String, String> = HashMap()
            hashMap.put(SKILL_KEY, title)
            hashMap.put(EXPERIENCE_KEY, experience)
            return hashMap
        }
    }

    @Parcelize
    data class SkillHeader(var title: String) : Item, Parcelable {
        override fun getContent(): HashMap<String, String> {
            return HashMap()
        }
    }

    fun getContent(): HashMap<String, String>
}