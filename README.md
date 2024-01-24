# BookMyShow Automation Workflow

This repository contains an automated workflow using Java and Selenium for the BookMyShow website. The workflow includes the following steps:

1. Open the [BookMyShow Explore Page](https://in.bookmyshow.com/explore/home/).
2. If prompted for a city, the script will automatically select Bengaluru.
3. Click on the "Sign In" button.
4. Click on "Continue with Email."
5. Enter the email address selauto@yopmail.com and click "Continue." (You can use any word in place of "selauto" if needed, like testusersel or seleniumauto.)
6. Navigate to [Yopmail](https://www.yopmail.com/) and log in using the email address selauto@yopmail.com to access the inbox (No password required).
7. Retrieve the latest email from BookMyShow and fetch the OTP.
8. Return to the BookMyShow Sign In page and enter the OTP.
9. Validate that the user is successfully signed in, and "Hi, Guest" is displayed.

## Tech Stack

- Java
- Selenium

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/vjanhavi28/BookMyShow_VaishnaviMacharde.git
