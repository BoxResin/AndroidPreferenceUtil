package boxresin.android.preference

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.preference.PreferenceManager
import boxresin.android.core.appContext

/** [SharedPreferences] 유틸리티 */
object Setting
{
	private val sp: SharedPreferences by lazy {
		PreferenceManager.getDefaultSharedPreferences(appContext)
	}

	/** 지정한 [key]에 해당하는 값을 디폴트 프리퍼런스에서 읽어온다. */
	operator fun <T> get(key: Key<T>): T = key.loadFrom(sp)

	/** 지정한 [key]와 [value]를 디폴트 프리퍼런스에 저장한다. */
	operator fun <T> set(key: Key<T>, value: T) = key.saveTo(sp, value)

	/** 지정한 [key]가 디폴트 프리퍼런스에 저장되어 있는지 여부를 반환한다. */
	operator fun contains(key: Key<*>) = "$key" in sp

	/** 지정한 [key]를 디폴트 프리퍼런스에서 삭제한다. */
	fun remove(key: Key<*>) = sp.edit().remove("$key").apply()

	/** 모든 키를 디폴트 프리퍼런스에서 삭제한다. */
	fun clear() = sp.edit().clear().apply()
}

/** [SharedPreferences] 키 */
sealed class Key<T>(private val keyName: String,
					private val read: SharedPreferences.(String, T) -> T,
					private val write: Editor.(String, T) -> Editor,
					private val defaultValue: T)
{
	internal fun loadFrom(sp: SharedPreferences): T = sp.read(keyName, defaultValue)
	internal fun saveTo(sp: SharedPreferences, value: T) = sp.edit().write(keyName, value).apply()

	override fun toString() = keyName
}

/** [String] 타입만 읽고 쓸 수 있는 프리퍼런스 키 */
class StringKey(keyName: String, defaultValue: String = "") :
		Key<String>(keyName, SharedPreferences::getString, Editor::putString, defaultValue)

/** [Int] 타입만 읽고 쓸 수 있는 프리퍼런스 키 */
class IntKey(keyName: String, defaultValue: Int = 0) :
		Key<Int>(keyName, SharedPreferences::getInt, Editor::putInt, defaultValue)

/** [Long] 타입만 읽고 쓸 수 있는 프리퍼런스 키 */
class LongKey(keyName: String, defaultValue: Long = 0L) :
		Key<Long>(keyName, SharedPreferences::getLong, Editor::putLong, defaultValue)

/** [Float] 타입만 읽고 쓸 수 있는 프리퍼런스 키 */
class FloatKey(keyName: String, defaultValue: Float = 0f) :
		Key<Float>(keyName, SharedPreferences::getFloat, Editor::putFloat, defaultValue)

/** [Boolean] 타입만 읽고 쓸 수 있는 프리퍼런스 키 */
class BooleanKey(keyName: String, defaultValue: Boolean = false) :
		Key<Boolean>(keyName, SharedPreferences::getBoolean, Editor::putBoolean, defaultValue)
