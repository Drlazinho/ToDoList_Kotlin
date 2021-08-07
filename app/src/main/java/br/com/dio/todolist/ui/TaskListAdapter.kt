package br.com.dio.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.todolist.R
import br.com.dio.todolist.databinding.ItemTaskBinding
import br.com.dio.todolist.model.Task

class TaskListAdapter : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(DiffCallback()) {

    var btnChecked : (Task) -> Unit = {}
    var btnFailed : (Task) -> Unit = {}
    var btnEdit : (Task) -> Unit = {}
    var btnDelete : (Task) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder(
        private val binding: ItemTaskBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Task) {
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description
            binding.tvDate.text = "${item.date} ${item.hour}"
            binding.ivChecked.setOnClickListener {
                taskConcluid(item)
            }
            binding.ivFailed.setOnClickListener {
                taskFailed(item)
            }
            binding.ivEdit.setOnClickListener {
                taskEdit(item)
            }
            binding.ivDelete.setOnClickListener {
                taskDelete(item)
            }

        }

        private fun taskConcluid(item: Task) {
            val ivChecked = binding.ivChecked
            ivChecked.setOnClickListener {
                when (it.id) {R.id.iv_checked -> btnChecked(item)
                }
                itemView.setBackgroundResource(R.color.green)
            }
        }
        private fun taskFailed(item: Task) {
            val ivFailed = binding.ivFailed
            ivFailed.setOnClickListener {
                when (it.id) {R.id.iv_failed -> btnFailed(item)}
            }
            itemView.setBackgroundResource(R.color.red)
        }
        private fun taskEdit(item: Task) {
            val ivEdit = binding.ivEdit
            ivEdit.setOnClickListener {
                when (it.id) {R.id.iv_edit -> btnEdit(item)}
            }
        }

        private fun taskDelete(item: Task) {
            val ivDelete = binding.ivDelete
            ivDelete.setOnClickListener {
                when (it.id) {R.id.iv_delete -> btnDelete(item)}
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id
}