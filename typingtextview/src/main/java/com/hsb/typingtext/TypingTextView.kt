package com.hsb.typingtext

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import android.animation.ValueAnimator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.hsb.typingtextview.R

/**
 * Developed by Syed Haseeb
 * Updated on Feb 12, 2024
 *
 * XML Usage:
 *
 *   <com.hsb.typing.TypingTextView
 *         android:id="@+id/typingTextView"
 *         android:layout_width="0dp"
 *         android:layout_height="wrap_content"
 *         android:padding="@dimen/_10sdp"
 *         android:text="@string/something_went_bazinngistan"
 *         android:textAlignment="textStart"
 *         android:textColor="#000000"
 *         android:textSize="16sp"
 *         app:animSpeed="20"
 *         app:fadeOnEnd="true"
 *         app:layout_constraintBottom_toTopOf="@+id/startBtn"
 *         app:layout_constraintEnd_toEndOf="parent"
 *         app:layout_constraintStart_toStartOf="parent"
 *         app:repeatMode="restart"
 *         app:reversed="false" />
 *
 */
class TypingTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var textToAnimate: CharSequence = ""
    private var index = 0
    private val handler = Handler(Looper.getMainLooper())
    private var typingSpeedMillis: Long = 100
    private var repeatMode: Int = ValueAnimator.RESTART
    private var reversed: Boolean = false
    private var fadeOnEnd: Boolean = false

    private var fadeAnim: Animation

    //Repeat Modes
    private val repeat = 0
    private val reverse = 1
    private val noRepeat = 2

    init {
        // Retrieve the initial text from the default TextView
        textToAnimate = "$text"
        text = null
        fadeAnim = AnimationUtils.loadAnimation(context, R.anim.fade_out)

        // Retrieve custom attributes
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TypingTextView)
        typingSpeedMillis = typedArray.getInt(
            R.styleable.TypingTextView_animSpeed,
            100
        ).toLong()
        repeatMode = typedArray.getInt(
            R.styleable.TypingTextView_repeatMode,
            ValueAnimator.RESTART
        )
        reversed = typedArray.getBoolean(R.styleable.TypingTextView_reversed, false)
        fadeOnEnd = typedArray.getBoolean(R.styleable.TypingTextView_fadeOnEnd, false)
        typedArray.recycle()

        startTypingAnimation()
    }

    private fun startTypingAnimation() {
        if (reversed) {
            reverseText()
        }
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (index < textToAnimate.length) {
                    append(textToAnimate[index].toString())
                    index++
                    handler.postDelayed(this, typingSpeedMillis)
                } else {
                    if (fadeOnEnd) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            this@TypingTextView.startAnimation(fadeAnim)
                        }, 800)
                    }
                    when (repeatMode) {
                        repeat -> {
                            Handler(Looper.getMainLooper()).postDelayed({
                                index = 0
                                this@TypingTextView.text = ""
                                handler.postDelayed(this, typingSpeedMillis)
                            }, 1500)
                        }

                        reverse -> {
                            Handler(Looper.getMainLooper()).postDelayed({
                                index = textToAnimate.length
                                this@TypingTextView.text = ""
                                handler.postDelayed(this, typingSpeedMillis)
                            }, 1500)
                        }

                        noRepeat -> {
                            index = 0
                        }
                    }

                }
            }
        }, 1000)
    }

    private fun reverseText() {
        val originalText = textToAnimate
        val reversedText = originalText.reversed()
        textToAnimate = reversedText
    }

    // Public method to update the text to animate
    fun setTextToAnimate(text: CharSequence) {
        this.textToAnimate = text
        index = 0
        this.text = null
        startTypingAnimation()
    }
}

