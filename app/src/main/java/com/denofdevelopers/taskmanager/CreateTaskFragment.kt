package com.denofdevelopers.taskmanager

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.denofdevelopers.taskmanager.databinding.FragmentCreateTaskBinding
import com.denofdevelopers.taskmanager.ui.AppViewModelProvider
import com.denofdevelopers.taskmanager.ui.CreateTaskViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

/**
 * This dialog allows the user to enter information about a donut, either creating a new
 * entry or updating an existing one.
 */
class CreateTaskFragment : BottomSheetDialogFragment() {

    private val createTaskViewModel by viewModels<CreateTaskViewModel> { AppViewModelProvider.Factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCreateTaskBinding.inflate(inflater, container, false).root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentCreateTaskBinding.bind(view)
        val args: CreateTaskFragmentArgs by navArgs()
        val taskId = args.itemId

        if (args.itemId > 0) {
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    createTaskViewModel.getTaskStream(args.itemId).filterNotNull().collect { item ->
                        with(binding) {
                            taskTitle.setText(item.title)
                            taskDescription.setText(item.description)
                        }
                    }
                }
            }
        }

        binding.taskTitle.doOnTextChanged { _, start, _, count ->
            binding.saveButton.isEnabled = (start + count) > 0
        }

        binding.saveButton.setOnClickListener {
            createTaskViewModel.saveTask(
                taskId,
                binding.taskTitle.text.toString(),
                binding.taskDescription.text.toString(),
            )
            dismiss()
        }
    }
}