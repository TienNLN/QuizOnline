USE [master]
GO
/****** Object:  Database [QuizOnline1]    Script Date: 26/02/2021 11:13:47 ******/
CREATE DATABASE [QuizOnline1]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuizOnline1', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\QuizOnline1.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'QuizOnline1_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\QuizOnline1_log.ldf' , SIZE = 2048KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [QuizOnline1] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuizOnline1].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuizOnline1] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuizOnline1] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuizOnline1] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuizOnline1] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuizOnline1] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuizOnline1] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuizOnline1] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuizOnline1] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuizOnline1] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuizOnline1] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuizOnline1] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuizOnline1] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuizOnline1] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuizOnline1] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuizOnline1] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuizOnline1] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuizOnline1] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuizOnline1] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuizOnline1] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuizOnline1] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuizOnline1] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuizOnline1] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuizOnline1] SET RECOVERY FULL 
GO
ALTER DATABASE [QuizOnline1] SET  MULTI_USER 
GO
ALTER DATABASE [QuizOnline1] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuizOnline1] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuizOnline1] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuizOnline1] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [QuizOnline1] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'QuizOnline1', N'ON'
GO
USE [QuizOnline1]
GO
/****** Object:  Table [dbo].[Question]    Script Date: 26/02/2021 11:13:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question](
	[questionID] [int] NOT NULL,
	[question] [nvarchar](500) NOT NULL,
	[createDate] [datetime] NOT NULL,
	[subjectID] [nvarchar](10) NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK__Question__6238D4921769151E] PRIMARY KEY CLUSTERED 
(
	[questionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Question_Choice]    Script Date: 26/02/2021 11:13:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question_Choice](
	[choiceID] [int] NOT NULL,
	[questionID] [int] NOT NULL,
	[isCorrect] [bit] NOT NULL,
	[choice] [nvarchar](500) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[choiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Quiz]    Script Date: 26/02/2021 11:13:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Quiz](
	[quizID] [int] NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[dateTakeQuiz] [datetime] NOT NULL,
	[timeTakeQuiz] [time](7) NOT NULL,
	[point] [float] NOT NULL,
	[subjectID] [nvarchar](10) NOT NULL,
	[questionAmount] [int] NOT NULL,
 CONSTRAINT [PK__Quiz__CFF54C1D458B33B8] PRIMARY KEY CLUSTERED 
(
	[quizID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Subject]    Script Date: 26/02/2021 11:13:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subject](
	[subjectID] [nvarchar](10) NOT NULL,
	[subjectName] [nvarchar](50) NOT NULL,
	[status] [bit] NOT NULL,
	[timeTakeQuiz] [time](7) NOT NULL,
	[questionAmount] [int] NOT NULL,
 CONSTRAINT [PK_Subject] PRIMARY KEY CLUSTERED 
(
	[subjectID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[User]    Script Date: 26/02/2021 11:13:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[email] [nvarchar](50) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[password] [nvarchar](100) NOT NULL,
	[role] [nchar](10) NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK__User__AB6E6165C14380A8] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[User_Question_Answer]    Script Date: 26/02/2021 11:13:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User_Question_Answer](
	[quizID] [int] NOT NULL,
	[questionID] [int] NOT NULL,
	[choice] [nvarchar](500) NOT NULL,
	[isCorrect] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[quizID] ASC,
	[questionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Question] ([questionID], [question], [createDate], [subjectID], [status]) VALUES (1, N'qweqweqwee12313', CAST(N'2021-02-23 10:11:23.590' AS DateTime), N'PRJ311', 1)
INSERT [dbo].[Question] ([questionID], [question], [createDate], [subjectID], [status]) VALUES (2, N'qweqweqwee12', CAST(N'2021-02-23 10:12:11.677' AS DateTime), N'PRJ311', 1)
INSERT [dbo].[Question] ([questionID], [question], [createDate], [subjectID], [status]) VALUES (3, N'asdadsasdasd', CAST(N'2021-02-23 00:00:00.000' AS DateTime), N'PRJ311', 1)
INSERT [dbo].[Question] ([questionID], [question], [createDate], [subjectID], [status]) VALUES (4, N'asfasf', CAST(N'2021-02-24 00:00:00.000' AS DateTime), N'PRJ311', 1)
INSERT [dbo].[Question] ([questionID], [question], [createDate], [subjectID], [status]) VALUES (5, N'afgagagag', CAST(N'2021-02-26 00:00:00.000' AS DateTime), N'PRJ311', 1)
INSERT [dbo].[Question] ([questionID], [question], [createDate], [subjectID], [status]) VALUES (6, N'qweqweqwee12', CAST(N'2021-02-26 10:45:17.730' AS DateTime), N'PRJ321', 1)
INSERT [dbo].[Question] ([questionID], [question], [createDate], [subjectID], [status]) VALUES (7, N'asdasd123', CAST(N'2021-02-26 10:48:35.227' AS DateTime), N'PRJ321', 1)
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (1, 1, 0, N'wrrrrrrrrrt')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (2, 1, 1, N'qerrrrrrrr')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (3, 1, 0, N'qtqtt')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (4, 1, 0, N'asdasf')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (5, 2, 0, N'wrrrrrrrrrt')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (6, 2, 0, N'qerrrrrrrr')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (7, 2, 1, N'qtqtt')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (8, 2, 0, N'asdasf')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (9, 3, 0, N'wrrrrrrrrrt')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (10, 3, 0, N'qerrrrrrrr')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (11, 3, 0, N'qtqtt')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (12, 3, 1, N'asdasf')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (13, 4, 1, N'asdasd')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (14, 4, 0, N'asdas')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (15, 4, 0, N'ssaffaf')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (16, 4, 0, N'wetwet')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (17, 5, 1, N'aafagaga')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (18, 5, 0, N'htrassd')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (19, 5, 0, N'agagaga')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (20, 5, 0, N'bzczbzcb')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (21, 6, 0, N'wrrrrrrrrrt')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (22, 6, 0, N'qerrrrrrrr')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (23, 6, 0, N'qtqtt')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (24, 6, 1, N'asdasf')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (25, 7, 0, N'wrrrrrrrrrt')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (26, 7, 1, N'qerrrrrrrr')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (27, 7, 0, N'qtqtt')
INSERT [dbo].[Question_Choice] ([choiceID], [questionID], [isCorrect], [choice]) VALUES (28, 7, 0, N'asdasf')
INSERT [dbo].[Quiz] ([quizID], [email], [dateTakeQuiz], [timeTakeQuiz], [point], [subjectID], [questionAmount]) VALUES (1, N'tien20001810@gmail.com', CAST(N'2021-02-26 07:39:19.000' AS DateTime), CAST(N'01:20:00' AS Time), 0, N'PRJ321', 5)
INSERT [dbo].[Quiz] ([quizID], [email], [dateTakeQuiz], [timeTakeQuiz], [point], [subjectID], [questionAmount]) VALUES (2, N'tien20001810@gmail.com', CAST(N'2021-02-26 07:54:48.000' AS DateTime), CAST(N'01:00:00' AS Time), 2, N'PRJ311', 5)
INSERT [dbo].[Quiz] ([quizID], [email], [dateTakeQuiz], [timeTakeQuiz], [point], [subjectID], [questionAmount]) VALUES (3, N'tien20001810@gmail.com', CAST(N'2021-02-26 07:56:50.000' AS DateTime), CAST(N'01:00:00' AS Time), 2, N'PRJ311', 5)
INSERT [dbo].[Quiz] ([quizID], [email], [dateTakeQuiz], [timeTakeQuiz], [point], [subjectID], [questionAmount]) VALUES (4, N'tien20001810@gmail.com', CAST(N'2021-02-26 08:27:37.000' AS DateTime), CAST(N'01:00:00' AS Time), 6, N'PRJ311', 5)
INSERT [dbo].[Quiz] ([quizID], [email], [dateTakeQuiz], [timeTakeQuiz], [point], [subjectID], [questionAmount]) VALUES (5, N'tien20001810@gmail.com', CAST(N'2021-02-26 08:29:10.000' AS DateTime), CAST(N'01:00:00' AS Time), 4, N'PRJ311', 5)
INSERT [dbo].[Quiz] ([quizID], [email], [dateTakeQuiz], [timeTakeQuiz], [point], [subjectID], [questionAmount]) VALUES (6, N'tien20001810@gmail.com', CAST(N'2021-02-26 08:31:41.000' AS DateTime), CAST(N'01:00:00' AS Time), 2, N'PRJ311', 5)
INSERT [dbo].[Subject] ([subjectID], [subjectName], [status], [timeTakeQuiz], [questionAmount]) VALUES (N'PRJ311', N'Java Desktop', 1, CAST(N'01:00:00' AS Time), 5)
INSERT [dbo].[Subject] ([subjectID], [subjectName], [status], [timeTakeQuiz], [questionAmount]) VALUES (N'PRJ321', N'Java Web', 1, CAST(N'01:20:00' AS Time), 5)
INSERT [dbo].[User] ([email], [name], [password], [role], [status]) VALUES (N'hoangnt20@fpt.edu.vn', N'hoang', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'lecturer  ', 1)
INSERT [dbo].[User] ([email], [name], [password], [role], [status]) VALUES (N'hoangnt23@fe.edu.vn', N'Tien Nguyen', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'student   ', 1)
INSERT [dbo].[User] ([email], [name], [password], [role], [status]) VALUES (N'longnh@fpt.edu.vn', N'long', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'student   ', 1)
INSERT [dbo].[User] ([email], [name], [password], [role], [status]) VALUES (N'tien20001810@gmail.com', N'tien', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'student   ', 1)
INSERT [dbo].[User] ([email], [name], [password], [role], [status]) VALUES (N'tiennlnse@fpt.edu.vn', N'Tien Nguyen', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'student   ', 1)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (2, 1, N'qerrrrrrrr', 1)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (2, 2, N'wrrrrrrrrrt', 0)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (2, 3, N'qerrrrrrrr', 0)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (2, 4, N'wetwet', 0)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (2, 5, N'NOT ANSWER', 0)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (3, 1, N'qerrrrrrrr', 1)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (3, 2, N'wrrrrrrrrrt', 0)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (3, 3, N'qerrrrrrrr', 0)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (3, 4, N'asdas', 0)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (3, 5, N'bzczbzcb', 0)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (4, 1, N'qerrrrrrrr', 1)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (4, 2, N'qtqtt', 1)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (4, 3, N'qerrrrrrrr', 0)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (4, 4, N'asdasd', 1)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (4, 5, N'bzczbzcb', 0)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (5, 1, N'qerrrrrrrr', 1)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (5, 2, N'wrrrrrrrrrt', 0)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (5, 3, N'asdasf', 1)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (5, 4, N'asdas', 0)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (5, 5, N'agagaga', 0)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (6, 1, N'wrrrrrrrrrt', 0)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (6, 2, N'qerrrrrrrr', 0)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (6, 3, N'qtqtt', 0)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (6, 4, N'asdasd', 1)
INSERT [dbo].[User_Question_Answer] ([quizID], [questionID], [choice], [isCorrect]) VALUES (6, 5, N'agagaga', 0)
ALTER TABLE [dbo].[Question]  WITH CHECK ADD  CONSTRAINT [FK_Question_Subject] FOREIGN KEY([subjectID])
REFERENCES [dbo].[Subject] ([subjectID])
GO
ALTER TABLE [dbo].[Question] CHECK CONSTRAINT [FK_Question_Subject]
GO
ALTER TABLE [dbo].[Question_Choice]  WITH CHECK ADD  CONSTRAINT [FK__Question___quest__1A14E395] FOREIGN KEY([questionID])
REFERENCES [dbo].[Question] ([questionID])
GO
ALTER TABLE [dbo].[Question_Choice] CHECK CONSTRAINT [FK__Question___quest__1A14E395]
GO
ALTER TABLE [dbo].[Quiz]  WITH CHECK ADD  CONSTRAINT [FK__Quiz__email__1273C1CD] FOREIGN KEY([email])
REFERENCES [dbo].[User] ([email])
GO
ALTER TABLE [dbo].[Quiz] CHECK CONSTRAINT [FK__Quiz__email__1273C1CD]
GO
ALTER TABLE [dbo].[Quiz]  WITH CHECK ADD  CONSTRAINT [FK_Quiz_Subject] FOREIGN KEY([subjectID])
REFERENCES [dbo].[Subject] ([subjectID])
GO
ALTER TABLE [dbo].[Quiz] CHECK CONSTRAINT [FK_Quiz_Subject]
GO
ALTER TABLE [dbo].[User_Question_Answer]  WITH CHECK ADD  CONSTRAINT [FK__User_Ques__quest__1DE57479] FOREIGN KEY([questionID])
REFERENCES [dbo].[Question] ([questionID])
GO
ALTER TABLE [dbo].[User_Question_Answer] CHECK CONSTRAINT [FK__User_Ques__quest__1DE57479]
GO
ALTER TABLE [dbo].[User_Question_Answer]  WITH CHECK ADD  CONSTRAINT [FK__User_Ques__quizI__1CF15040] FOREIGN KEY([quizID])
REFERENCES [dbo].[Quiz] ([quizID])
GO
ALTER TABLE [dbo].[User_Question_Answer] CHECK CONSTRAINT [FK__User_Ques__quizI__1CF15040]
GO
USE [master]
GO
ALTER DATABASE [QuizOnline1] SET  READ_WRITE 
GO
