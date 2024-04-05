**Projet RAG with Spring AI and Thymeleaf**

This project is a demonstration of using Retrieval Augmented Generation (RAG) in a web application using Spring AI and Thymeleaf. 
The goal of this project is to create a user-friendly user interface to automatically generate answers to questions using a RAG model based on personal source of data like pdfs documents.
Basically you have to put your source of data (pdf files) and ask chat-gpt to answer your question based on your data

__Features__

Automatic generation of question answers using a pre-trained RAG model.
User-friendly user interface to enter questions and view generated answers.
Integration with Spring Boot for web application management.
Use of Thymeleaf for creating HTML views.

__Prerequisites__

Before you begin, make sure you have the following installed:

- Java JDK (version 17 or higher)
- Maven
- A modern web browser (Chrome, Firefox, etc.)
- A token from open-ai (https://platform.openai.com/api-keys)

__Installation and Getting Started__

1. Clone this repository to your local machine:

```bash
$ git clone  https://github.com/ousmane12/rag.git
```

2. Navigate to the project directory:

```bash
$ cd rag
```
3. Add your open-ai token in your env variable or inside the application.properties file
4. Add your source of data under _ressources/pdf_ repository
5. Using IntelliJ, you can run the application
6. Access the application in your web browser at the following address:
   ```
   http://localhost:8899/rag
   ```
__Use__

Once the application is launched, you will be redirected to the home page where you can ask a question in the input field provided for this purpose. Press the “Search” button to get an automatic response to your question.

As an example, I used some informations regarding my old school (www.esp.sn) to ask some questions about their programs:

![capture test](chemin/vers/image.png)


__Improvements__

As an improvement, it will be great to directly upload our documents from the browser. Stay tuned for that release 😁

__Contribution__

Contributions are welcome! If you would like to contribute to this project, please follow these steps:

Fork the project
Create a branch for your feature (git checkout -b feature/NewFeature)
Commit your changes (git commit -am 'Added new functionality')
Push to branch (git push origin feature/New Feature)
Create a new pull request
   
