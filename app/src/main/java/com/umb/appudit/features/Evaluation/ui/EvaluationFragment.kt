package com.umb.appudit.features.Evaluation.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.umb.appudit.R
import com.umb.appudit.features.Evaluation.ui.viewmodel.EvaluationViewModel

class EvaluationFragment : Fragment() {

    companion object {
        fun newInstance() = EvaluationFragment()
    }

    private lateinit var viewModel: EvaluationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.evaluation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EvaluationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
