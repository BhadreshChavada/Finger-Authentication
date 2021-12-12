# Finger-Authentication

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  
  Step 2. Add the dependency
  dependencies {
	        implementation 'com.github.BhadreshChavada:Finger-Authentication:1.0'
	}
  
  Step 3. Call displayBiometricPrompt Method as per your requirement.
  
  Authentication(this, object : AuthResult {
            override fun deviceHaveNotAuth() {
                Toast.makeText(
                    applicationContext,
                    "Device not support",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun userHaveNotAddedFinger() {
                Toast.makeText(
                    applicationContext,
                    "Finger not found",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun authFail() {
                Toast.makeText(
                    applicationContext,
                    "Authentication Fail",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun authSuccessful() {
                Toast.makeText(
                    applicationContext,
                    "Authentication Successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun authUsingUserCredentials() {
                Toast.makeText(
                    applicationContext,
                    "Open application Auth screen",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }).displayBiometricPrompt()
        
        Step 4 (Optional):
        
        Pass the title and subtitle of prompt dialog.
        
  
  
