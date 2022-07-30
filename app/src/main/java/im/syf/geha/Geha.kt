package im.syf.geha

import android.app.Application
import im.syf.geha.data.DummyDataSource

class Geha : Application() {

    val dummyDataSource: DummyDataSource by lazy {
        DummyDataSource.getInstance(this)
    }
}
