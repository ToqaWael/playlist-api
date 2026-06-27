# How to Run the Playlist API

This guide walks you through everything you need to get this project running on your machine.

---

## What You Need to Install First

### 1. Java 17 or higher

Download from: https://www.oracle.com/java/technologies/downloads/

After installing, open a terminal and type:

```
java -version
```

You should see something like `java 17.x.x`. If you get an error, Java is not installed correctly.

> **Issue — "java: command not found"**
> Reinstall Java and make sure to check "Add to PATH" during installation.

---

### 2. MySQL

Download from: https://dev.mysql.com/downloads/installer/

During installation:

- Choose **Full** setup type
- When it asks for a password, set one and write it down — you will need it later
- On the **Type and Networking** screen — click Next, don't change anything (port 3306 is already set correctly)
- On the **Windows Service** screen — click Next, don't change anything (MySQL will auto-start with your PC)
- On the **Apply Configuration** screen — click **Execute** and wait for all steps to turn green
- Click **Finish**

> **Issue — MySQL not starting**
> On Windows, press the Windows key, search for **Services**, find **MySQL80** in the list, right-click it and click **Start**.

---

### 3. Postman

Either download app or open it directly in your browser at: https://web.postman.co

Sign up for a free account (Google sign in works) and you are ready to go.

---

## Step 1 — Get the Code

Go to https://github.com/ToqaWael/playlist-api, click the green **Code** button, then click **Download ZIP**.

Extract the ZIP file and open the folder in VS Code.

---

## Step 2 — Create the Database

Open **MySQL Workbench** (installed alongside MySQL).

Click **Local instance MySQL80** and enter your root password. You will see a query editor open.

In the query editor, type:

```sql
CREATE DATABASE playlistdb;
```

Press `Ctrl + Enter` to run it. You should see `playlistdb` appear in the left panel under Schemas.

> This creates an empty storage space for the app. The app will automatically fill it with tables when it runs.

> **Issue — "Access denied" when connecting**
> The password you're entering is wrong. Use the password you set during MySQL installation.

---

## Step 3 — Add Your Database Password to the Project

Open this file inside the project folder:

```
src/main/resources/application.properties
```

Replace `YOUR_MYSQL_PASSWORD_HERE` with your actual MySQL password:

```
spring.application.name=playlist-api
spring.datasource.url=jdbc:mysql://localhost:3306/playlistdb
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD_HERE
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Save the file.

---

## Step 4 — Run the Application

Open the terminal in VS Code (`Ctrl + backtick`) and type:

**On Windows:**

```
mvnw.cmd spring-boot:run
```

**On Mac/Linux:**

```
./mvnw spring-boot:run
```

The first time you run this, it will download some files automatically. Wait until you see:

```
Started PlaylistApiApplication in X seconds
```

Your API is now running at `http://localhost:8080`

> **Issue — "Port 8080 already in use"**
> Another app on your computer is using that port. Add this line to `application.properties`:
>
> ```
> server.port=8081
> ```
>
> Then use `http://localhost:8081` instead of `8080` in all requests below.

---

## Step 5 — Test with Postman

### First Time Setup

1. Go to https://web.postman.co and sign in or use the app
2. Once inside, it will create a default workspace for you — just click **Continue**
3. Click the **+** button at the top to open a new request tab

---

### How to Use It

At the top of the new tab you will see:

```
[ GET ▼ ] [ enter url here ] [ Send ]
```

- The **dropdown** on the left is the request type (GET, POST, PUT, DELETE)
- The **URL bar** is where you type the address
- **Send** fires the request
- The **response** appears at the bottom — green **200 OK** means success, red means something went wrong

For requests that send data (POST, PUT) you need to set the Body:

1. Click the **Body** tab below the URL bar
2. Select **raw**
3. Change the dropdown on the right from **Text** to **JSON**
4. Paste your data in the text area below

---

### Create a Playlist

1. Set method to **POST**, URL to `http://localhost:8080/api/playlists`
2. Body → raw → JSON → paste:

```json
{
  "name": "My Favorites",
  "songs": []
}
```

3. Click **Send**

Response (playlist saved with auto-assigned id):

```json
{
  "id": 1,
  "name": "My Favorites",
  "songs": []
}
```

---

### Add a Song to a Playlist

1. Set method to **POST**, URL to `http://localhost:8080/api/playlists/My Favorites/songs`
2. Body → raw → JSON → paste:

```json
{
  "title": "Blinding Lights",
  "artist": "The Weeknd"
}
```

3. Click **Send**

Response (playlist returned with the song inside it):

```json
{
  "id": 1,
  "name": "My Favorites",
  "songs": [
    {
      "id": 1,
      "title": "Blinding Lights",
      "artist": "The Weeknd"
    }
  ]
}
```

---

### Get All Playlists

This returns every playlist stored in the database.

1. Set the method to **GET**
2. Enter the URL: `http://localhost:8080/api/playlists`
3. No body needed — just click **Send**

You will get back a list of all playlists.

---

### Get a Specific Playlist by Name

1. Set the method to **GET**
2. Enter the URL: `http://localhost:8080/api/playlists/My Favorites`
3. Click **Send**

You will get back that specific playlist with all its songs.

---

### Update a Playlist Name

1. Set the method to **PUT**
2. Enter the URL: `http://localhost:8080/api/playlists/My Favorites`
3. Click **Body** → **raw** → **JSON**
4. Paste:

```json
{
  "name": "New Name",
  "songs": []
}
```

5. Click **Send**

The playlist name will be updated.

---

### Delete a Playlist

1. Set the method to **DELETE**
2. Enter the URL: `http://localhost:8080/api/playlists/My Favorites`
3. No body needed — just click **Send**

The playlist and all its songs will be deleted.

---

## How to Stop the Application

In the terminal where the app is running, press `Ctrl + C`.
