package com.inplayersdk.social_urls

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inplayersdk.R
import com.inplayersdk.databinding.ActivityMainBinding
import com.sdk.inplayer.callback.InPlayerCallback
import com.sdk.inplayer.configuration.InPlayer
import com.sdk.inplayer.model.account.InPlayerSocialUrls

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        loadSocialUrls()
    }

    private fun loadSocialUrls() {
        InPlayer.Account.getSocialLoginUrls(
            "viktor.inplayer://",
            InPlayerCallback { socialUrlsList, error ->
                if (error == null) {
                    configureRecyclerView(socialUrlsList)
                } else {
                    //Handle Error
                }
            })
    }


    override fun onResume() {
        super.onResume()

        intent.data?.let { uri ->

            InPlayer.Account.validateSocialLoginToken(uri, InPlayerCallback { value, error ->
                if (error == null) {
                    binding.contentLogin.tvWelcomeUser.text = "Welcome ${value.fullName}"
                } else {
                    binding.contentLogin.tvWelcomeUser.text = "Error executing request ${error.errorsList}"
                }
            })

        }
    }


    private fun configureRecyclerView(list: ArrayList<InPlayerSocialUrls>) {
        binding.contentLogin.rvSocialButton.apply {
            adapter = SocialUrlsAdapter(list)
            layoutManager = LinearLayoutManager(this@LoginActivity)

        }
    }


    inner class SocialUrlsAdapter(private val list: ArrayList<InPlayerSocialUrls>) :
        RecyclerView.Adapter<SocialUrlsAdapter.SocialUrlViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialUrlViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_social_login, parent, false)
            return SocialUrlViewHolder(view)
        }

        override fun getItemCount() = list.size

        override fun onBindViewHolder(holder: SocialUrlViewHolder, position: Int) {
            holder.bind(list[position])
        }

        inner class SocialUrlViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            var socialButton: AppCompatButton = itemView.findViewById(R.id.btn_social)

            fun bind(socialUrls: InPlayerSocialUrls) {
                socialButton.text = socialUrls.socialAuthkName.capitalize()

                socialButton.setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(socialUrls.socialAuthUrl)))
                }
            }
        }
    }

}
