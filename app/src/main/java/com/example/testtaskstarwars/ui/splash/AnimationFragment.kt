package com.example.testtaskstarwars.ui.splash

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.navigation.fragment.findNavController
import com.example.testtaskstarwars.R
import com.example.testtaskstarwars.databinding.FragmentSplashBinding
import com.example.testtaskstarwars.domain.ANIMATION_DURATION
import com.example.testtaskstarwars.domain.ANIMATION_MOVE
import com.example.testtaskstarwars.domain.ANIMATION_ROTATION
import com.example.testtaskstarwars.domain.ANIMATION_SIZE
import com.google.android.material.bottomnavigation.BottomNavigationView


class AnimationFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private var animatorSet: AnimatorSet? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val tvStarWars = binding.tvStarWars

        //move text up
        val moveAnimator = ObjectAnimator.ofFloat(tvStarWars, "translationY", ANIMATION_MOVE)

        // make text smaller
        val scaleAnimatorX = ObjectAnimator.ofFloat(tvStarWars, "scaleX", ANIMATION_SIZE)
        val scaleAnimatorY = ObjectAnimator.ofFloat(tvStarWars, "scaleY", ANIMATION_SIZE)

        // text at an angle
        val rotationAnimator = ObjectAnimator.ofFloat(tvStarWars, "rotationX", ANIMATION_ROTATION)

        val animatorSet = AnimatorSet()
        animatorSet.apply {
            playTogether(moveAnimator, scaleAnimatorX, scaleAnimatorY, rotationAnimator)
            duration = ANIMATION_DURATION
            interpolator = AccelerateInterpolator()

            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    if (isAdded) {
                        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility =
                            View.VISIBLE
                        findNavController().navigate(R.id.action_animationFragment_to_navigation_home)
                    }
                }
            })
            start()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //used this method (onActivityCreated) cause otherwise the app crashes
        requireActivity().findViewById<BottomNavigationView>(R.id.nav_view).visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //otherwise the app crashes if screen orientation changes
        animatorSet?.cancel()
        animatorSet = null
        _binding = null
    }
}