# RXpect ![Build Status](https://travis-ci.org/ybonjour/RXpect.svg?branch=master)
RXpect extends Mockito to simplify unit testing for RX Kotlin applications.
It takes care of synchronizing your test with your application code.

### Test with expectations
```kotlin
class MyPresenter(private val view: MyView) {
    fun onResume() {
        disposable = users.getUsers().subscribeBy {
            onSuccess = { view.show(it)}
            onError = { println("ERROR: $it") }
        }
    }
}
```

```kotlin
@Test
fun userIsShown() {
    whenever(users.getUsers()).thenReturn(Single.just(user))
    val expectation = expect(view.show(user))
    
    presenter.onResume()
    
    expectation.verify()
}
```

### Test subscribe
```kotlin
class MyPresenter {
    fun onResume() {
        users.getUsers().subscribeBy {
            onSuccess = ::println
            onError = { println("ERROR: $it") }
        }
    }
}
```

```kotlin
@Test
fun showsUser() {
    val expectation = expectSubscribe(users.getUsers()).thenEmit(user)
    
    presenter.onResume()
        
    expectation.verify()
    
}
```

### Test dispose
```kotlin
class MyPresenter(private val users: Users) {
    private var disposable: Disposable? = null
    
    fun onResume() {
        disposable = users.getUsers().subscribeBy {
            onSuccess = ::println
            onError = { println("ERROR: $it") }
        }
    }
    
    fun onPause() {
        disposable?.dispose()
    }
}
```

```kotlin
@Test
fun loadingUsersDisposedOnPause() {
    val expectation = expectDispose(users.getUsers()) 
    
    presenter.onResume()
    presenter.onPause()
    
    expectation.verify()
}
```
