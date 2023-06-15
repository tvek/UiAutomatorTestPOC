package `in`.tvek.widgetapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.EventCondition
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    val mDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Test
    fun useAppContext() {
        mDevice.pressHome()
        val launcherPackage: String = "com.google.android.apps.nexuslauncher"
        assertEquals(launcherPackage, mDevice.currentPackageName)
    }

    @Test
    fun testMultiGesture(){
        mDevice.swipe(300,700, 300, 100, 200)
        val element:UiObject2 = mDevice.findObject(By.text("Search your phone and more"))
        assertNotNull(element)
    }

    @Test
    fun testButtonWithText(){
        val gmail: UiObject2 = mDevice.findObject(By.text("Gmail"))
        val opened: Boolean = gmail.clickAndWait(Until.newWindow(), 3000)
        assert(opened)
    }

    @Test
    fun testButtonTap(){
        val okButton: UiObject2 = mDevice.findObject(
            By.text("Allow").clazz("android.widget.Button")
        )
        if (okButton != null) {
            val bySelector:BySelector = By.text("hidden_element")
            okButton.click()
            mDevice.wait(Until.findObject(bySelector), 500);
            val interalObject:UiObject2 = mDevice.findObject(bySelector)
            assertNotNull(interalObject)
        }

    }

    @Test
    fun testFindNonTextElement(){
        val okButton: List<UiObject2> = mDevice.findObjects(
            By.clickable(true)
        )
        var status = false

        if (false && okButton != null && okButton.size > 2) {
            okButton[1].click()
            status = true
        }
        assertTrue(status)

    }

    @Test
    fun testListScrollingWithoutID(){
        val list: List<UiObject2> = mDevice.findObjects(
            By.scrollable(true)
        )
        if (list != null && list.size > 0) {
            list[0].scroll(Direction.DOWN, 0.50F)
            Until.scrollFinished(Direction.DOWN)
            val elementInList = mDevice.findObject(
                By.text("2")
            )
            val status : Boolean = elementInList.clickAndWait(Until.newWindow(), 5000)
            assertTrue(status)
        }
    }

}