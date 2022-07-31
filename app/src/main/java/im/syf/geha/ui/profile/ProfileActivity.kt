package im.syf.geha.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import im.syf.geha.R
import im.syf.geha.ui.search.User

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Retrieve argument passed via intent
        val user: User = requireNotNull(intent.getParcelableExtra(EXTRA_USER_KEY))
    }

    companion object {
        const val EXTRA_USER_KEY = "extra_user_key"
    }
}
