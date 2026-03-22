# NavData Parcel - Parcelable Read/Write Mismatch Bug Reproduction

Android project to reproduce a Parcelable deserialization crash caused by a read/write mismatch.

## Bug Summary

**Symptom**: App crashes when launching DestinationActivity with NavigationData passed via Intent extras.

**Error**:
```
Unparcelling of ... NavigationData of type VAL_PARCELABLE consumed 88 bytes, but 92 expected. [throwing]
```

**Root Cause**: In `NavigationData`, the field `field3` is **written** in `writeToParcel()` but **never read** in the parcel constructor. This causes a 4-byte deserialization mismatch that Android's Parcel validator detects and throws.

---

## How to Reproduce the Crash

1. **Open** the project in Android Studio (or use command line)
2. **Install** on your device
3. **Tap** the "Launch Destination (CRASH)" button on the main screen
4. **Expected**: App crashes **before** the second screen appears

The crash occurs when `DestinationActivity.onCreate()` calls `getIntent().getParcelableExtra("navigation_data")` — the system fails while deserializing the Parcelable from the Intent Bundle.

---

## The Fix

Add the missing read in `NavigationData.kt`, in the parcel constructor, **after** `field2`:

```kotlin
field1 = parcel.readString()
field2 = parcel.readString()   
field3 = parcel.readString() // ← ADD THIS LINE
```

**Rule**: Parcel read order **must exactly match** write order. Every field written in `writeToParcel` must be read in the constructor in the same sequence.

## License

[Apache License Version 2.0](LICENSE)
