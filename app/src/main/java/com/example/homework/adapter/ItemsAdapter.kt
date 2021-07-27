package com.example.homework.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.FilterFragment
import com.example.homework.MainActivity
import com.example.homework.R
import com.example.homework.model.Item
import com.example.homework.presenter.MainActivityPresenter
import java.util.*
import androidx.fragment.app.FragmentManager as FragmentManager


class ItemsAdapter(
    private val data: List<Item>,
    val activity: MainActivity,
    val presenter: MainActivityPresenter
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<Item> = data
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            VIEW_TYPE_SKILLS_HEADER -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_skills_header, parent, false)
                return SkillsTitleHeaderViewHolder(itemView)
            }
            VIEW_TYPE_SKILL -> {
                val itemView =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_skill, parent, false)
                return SkillViewHolder(itemView)
            }
            VIEW_TYPE_HEADER -> {
                val itemView =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
                return HeaderViewHolder(itemView)
            }
            VIEW_TYPE_PROJECT_DESCRIPTION -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_project_description, parent, false)
                return ProjectDescriptionViewHolder(itemView)
            }
            else ->
                throw IllegalArgumentException("")
        }
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (items[position]) {
            is Item.ProjectDescription -> {
                val content: HashMap<String, String> = items[position].getContent()
                val projectDescriptionViewHolder: ProjectDescriptionViewHolder =
                    holder as ProjectDescriptionViewHolder
                projectDescriptionViewHolder.projectDescription.setText(content.get("DESCRIPTION_KEY"))
            }
            is Item.Header -> {
                val content: HashMap<String, String> = items[position].getContent()
                val headerViewHolder: HeaderViewHolder = holder as HeaderViewHolder
                headerViewHolder.userName.setText(content.get("NAME_KEY"))
                headerViewHolder.userSurname.setText(content.get("SURNAME_KEY"))
                headerViewHolder.userGrade.setText(content.get("GRADE_KEY"))
                headerViewHolder.gitButton.setOnClickListener(View.OnClickListener {
                    presenter.onGitHubButtonPressed()
                })
            }
            is Item.SkillItem -> {
                val content: HashMap<String, String> = items[position].getContent()
                val skillViewHolder: SkillViewHolder = holder as SkillViewHolder
                skillViewHolder.userExperience.setText(content.get("EXPERIENCE_KEY"))
                skillViewHolder.userSkill.setText(content.get("SKILL_KEY"))
            }
            is Item.SkillHeader -> {
                val skillsHeaderViewHolder: SkillsTitleHeaderViewHolder =
                    holder as SkillsTitleHeaderViewHolder
                skillsHeaderViewHolder.image.setOnClickListener(View.OnClickListener {
                    presenter.onFilterButtonPressed()
                })
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("ItemCount:", items.size.toString())
        return items.size
    }

    override fun getItemViewType(position: Int): Int =
        when (items[position]) {
            is Item.Header -> VIEW_TYPE_HEADER
            is Item.ProjectDescription -> VIEW_TYPE_PROJECT_DESCRIPTION
            is Item.SkillHeader -> VIEW_TYPE_SKILLS_HEADER
            is Item.SkillItem -> VIEW_TYPE_SKILL
            else -> -1
        }

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_PROJECT_DESCRIPTION = 1
        private const val VIEW_TYPE_SKILLS_HEADER = 2
        private const val VIEW_TYPE_SKILL = 3
    }


    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImage: ImageView? = itemView.findViewById(R.id.user_image)
        val userName: TextView = itemView.findViewById(R.id.user_name)
        val userSurname: TextView = itemView.findViewById(R.id.user_surname)
        val userGrade: TextView = itemView.findViewById(R.id.user_grade)
        val gitButton: Button = itemView.findViewById(R.id.git_button)


    }

    class SkillViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userSkill: TextView = itemView.findViewById(R.id.skill)
        val userExperience: TextView = itemView.findViewById(R.id.experience)
    }

    class ProjectDescriptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val projectTitle: TextView = itemView.findViewById(R.id.project_title)
        val projectDescription: TextView = itemView.findViewById(R.id.project_description)
    }

    class SkillsTitleHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.skills_title)
        val image: ImageView = itemView.findViewById(R.id.skills_title_image)
    }


}