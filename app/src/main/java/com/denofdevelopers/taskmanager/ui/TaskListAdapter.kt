package com.denofdevelopers.taskmanager.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.denofdevelopers.taskmanager.data.Task
import com.denofdevelopers.taskmanager.databinding.TaskItemBinding


class TaskListAdapter(
    private var onEdit: (Task) -> Unit,
    private var onDelete: (Task) -> Unit
) : ListAdapter<Task, TaskListAdapter.JuiceListViewHolder>(JuiceDiffCallback()) {

    class JuiceListViewHolder(
        private val binding: TaskItemBinding,
        private val onEdit: (Task) -> Unit,
        private val onDelete: (Task) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private val nameView = binding.title
        private val description = binding.description

        fun bind(task: Task) {
            nameView.text = task.title
            description.text = task.description
            binding.deleteButton.setOnClickListener {
                onDelete(task)
            }
            binding.root.setOnClickListener {
                onEdit(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = JuiceListViewHolder(
        TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onEdit,
        onDelete
    )

    override fun onBindViewHolder(holder: JuiceListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class JuiceDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}