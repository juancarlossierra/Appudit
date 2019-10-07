package com.umb.appudit.features.standard.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.umb.appudit.R
import com.umb.appudit.databinding.StandardFragmentBinding
import com.umb.appudit.features.standard.ui.viewmodel.StandardViewModel

class StandardFragment : Fragment() {

    private lateinit var viewDataBinding: StandardFragmentBinding

    companion object {
        fun newInstance() = StandardFragment()
    }

    private lateinit var viewModel: StandardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(
            inflater, R.layout.standard_fragment, container,
            false
        )
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StandardViewModel::class.java)
        viewDataBinding.viewmodel = viewModel
        viewDataBinding.lifecycleOwner = this
        //first spinner´s configuration
        viewModel.start()
        viewModel.fetchSpinnerNoRecuerdo().observe(this, Observer { spinnerData ->
            val spinnerAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerData)
            viewDataBinding.spinner.adapter = spinnerAdapter
        })

        //second spinner´s configuration
        viewModel.fetchSpinnerStandard().observe(this, Observer { spinnerData ->
            val spinnerAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerData)
            viewDataBinding.spinner2.adapter = spinnerAdapter
        })
    }
}
