package boxresin.android.preference

import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.AndroidJUnit4
import boxresin.android.core.appContext
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsTest
{
	@Before
	fun setup()
	{
		appContext = getInstrumentation().targetContext
	}

	@After
	fun release()
	{
		Setting.clear()
	}

	/** [StringKey] 읽기 쓰기 테스트 */
	@Test
	fun stringKey()
	{
		val key = StringKey(keyName = "test")
		Setting[key] = "hello"
		assertEquals("hello", Setting[key])
	}

	/** [IntKey] 읽기 쓰기 테스트 */
	@Test
	fun intKey()
	{
		val key = IntKey(keyName = "test")
		Setting[key] = 1234
		assertEquals(1234, Setting[key])
	}

	/** [LongKey] 읽기 쓰기 테스트 */
	@Test
	fun longKey()
	{
		val key = LongKey(keyName = "test")
		Setting[key] = 5678L
		assertEquals(5678L, Setting[key])
	}

	/** [FloatKey] 읽기 쓰기 테스트 */
	@Test
	fun floatKey()
	{
		val key = FloatKey(keyName = "test")
		Setting[key] = 3.14f
		assertEquals(3.14f, Setting[key])
	}

	/** [BooleanKey] 읽기 쓰기 테스트 */
	@Test
	fun booleanKey()
	{
		val key = BooleanKey(keyName = "test")
		Setting[key] = false
		assertEquals(false, Setting[key])
	}

	/** [Setting.contains] 테스트 */
	@Test
	fun contains()
	{
		val key = StringKey(keyName = "test")
		assertFalse(key in Setting)

		Setting[key] = "boxresin"
		assertTrue(key in Setting)
	}

	/** [Setting.remove] 테스트 */
	@Test
	fun remove()
	{
		val key = StringKey(keyName = "test")
		Setting[key] = "boxresin"

		assertEquals("boxresin", Setting[key])

		Setting.remove(key)
		assertFalse(key in Setting)
	}

	/** 키가 없을 때 기본 값으로 불러와지는지 테스트 */
	@Test
	fun defaultValue()
	{
		val key = BooleanKey(keyName = "test", defaultValue = true)
		assertTrue(Setting[key])
	}
}
