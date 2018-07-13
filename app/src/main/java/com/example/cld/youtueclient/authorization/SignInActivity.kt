package com.example.cld.youtueclient.authorization

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.cld.youtueclient.R
import com.example.cld.youtueclient.dataLayer.SERVER_CLIENT_ID
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.Scope
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.signin_activity.*
import android.content.Intent
import android.util.Log
import com.example.cld.youtueclient.MainActivity
import com.example.cld.youtueclient.dataLayer.SECRET_KEY
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class SignInActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

    val RC_AUTH_CODE = 10001

    override fun onCreate(savedInstanceState: Bundle?) {
        if (getSharedPreferences(getString(R.string.preferences_key),Context.MODE_PRIVATE).getString("email",null)!=null){
            startNextSreen()
            return
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_activity)


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestServerAuthCode(SERVER_CLIENT_ID)
                .requestEmail()
                .requestScopes(Scope("https://www.googleapis.com/auth/youtube.readonly"))
                .build()
        val mApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        signInButton.clicks()
                .subscribe {signIn(mApiClient)}
    }

    private fun signIn(mApiClient: GoogleApiClient) {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mApiClient)
        startActivityForResult(signInIntent, RC_AUTH_CODE)
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == RC_AUTH_CODE) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                val acct = result.signInAccount

                getSharedPreferences(getString(R.string.preferences_key),Context.MODE_PRIVATE)
                        .edit()
                        .putString("displayName",acct?.displayName)
                        .putString("email",acct?.email)
                        .apply()

                Log.d("qq","123   "+acct?.photoUrl.toString())
                val authCode = acct!!.serverAuthCode
                authCode?.let { getAccessToken(it) }

                startNextSreen()
            }
        }
    }

    fun startNextSreen(){
        startActivity(Intent(baseContext,MainActivity::class.java))
    }

    fun getAccessToken(authCode: String) {
        val client = OkHttpClient()

        val requestBody = FormBody.Builder()
                .add("grant_type", "authorization_code")
                .add("client_id", SERVER_CLIENT_ID)
                .add("client_secret", SECRET_KEY)
                .add("code", authCode)
                .build()

        val request = Request.Builder()
                .url("https://www.googleapis.com/oauth2/v4/token")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .post(requestBody)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call?, response: Response?) {
                val jsonObject = JSONObject(response?.body()?.string())
                val mAccessToken = jsonObject.get("access_token").toString()
                val mTokenType = jsonObject.get("token_type").toString()
//                val mRefreshToken = jsonObject.get("refresh_token").toString()
                getSharedPreferences(getString(R.string.preferences_key),Context.MODE_PRIVATE)
                        .edit()
                        .putString("mAccessToken",mAccessToken)
                        .putString("mTokenType",mTokenType)
                 //       .putString("mRefreshToken",mRefreshToken)
                        .apply()

            }
        })
    }
}
