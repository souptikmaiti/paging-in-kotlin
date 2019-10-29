package com.example.pagingwithkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pagingwithkotlin.R
import com.example.pagingwithkotlin.data.models.GitItem
import kotlinx.android.synthetic.main.git_item.view.*

class GitRepoAdapter:PagedListAdapter<GitItem, GitRepoAdapter.RepoViewHolder>(DIFF_CALLBACK ) {

    companion object{
        private val DIFF_CALLBACK  = object: DiffUtil.ItemCallback<GitItem>(){
            override fun areItemsTheSame(oldItem: GitItem, newItem: GitItem): Boolean {
                return oldItem.fullName == newItem.fullName
            }

            override fun areContentsTheSame(oldItem: GitItem, newItem: GitItem): Boolean {
                return oldItem == newItem
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.git_item, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        var gitRepo = getItem(position)
        holder.populate(gitRepo)
    }

    class RepoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun populate(gitRepo: GitItem?) {
            itemView.tv_name.text = gitRepo?.fullName
            itemView.tv_des.text = gitRepo?.description
            Glide.with(itemView.context).load(gitRepo?.owner?.avatarUrl).into(itemView.iv_profile)
            itemView.tv_fork.text = "fork: "+ gitRepo?.forks
        }
    }
}