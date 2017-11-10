USE [metadata]
GO
/****** Object:  Table [dbo].[NominalType]    Script Date: 2015/9/14 21:43:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NominalType](
    [id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
    [dateadded] [datetime] NOT NULL,
    [lastmodified] [datetime] NOT NULL,
    [lastmodifiedby] [varchar](255) NOT NULL,
    [endtime] [datetime] NULL,
    [starttime] [datetime] NULL,
    [description] [varchar](255) NULL,
    [innername] [varchar](255) NOT NULL,
    [name] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NominalValue]    Script Date: 2015/9/14 21:43:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NominalValue](
    [id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
    [dateadded] [datetime] NOT NULL,
    [lastmodified] [datetime] NOT NULL,
    [lastmodifiedby] [varchar](255) NOT NULL,
    [endtime] [datetime] NULL,
    [starttime] [datetime] NULL,
    [description] [varchar](255) NULL,
    [innername] [varchar](255) NOT NULL,
    [name] [varchar](255) NOT NULL,
    [nominalTypeId] [numeric](19, 0) NULL,
PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Variable]    Script Date: 2015/9/14 21:43:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Variable](
    [id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
    [dateadded] [datetime] NOT NULL,
    [lastmodified] [datetime] NOT NULL,
    [lastmodifiedby] [varchar](255) NOT NULL,
    [endtime] [datetime] NULL,
    [starttime] [datetime] NULL,
    [description] [varchar](255) NULL,
    [innername] [varchar](255) NOT NULL,
    [name] [varchar](255) NOT NULL,
    [defaultValue] [varchar](255) NOT NULL,
    [nominalTypeId] [numeric](19, 0) NULL,
    [typeId] [numeric](19, 0) NULL,
PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[VariableGroup]    Script Date: 2015/9/14 21:43:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[VariableGroup](
    [id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
    [dateadded] [datetime] NOT NULL,
    [lastmodified] [datetime] NOT NULL,
    [lastmodifiedby] [varchar](255) NOT NULL,
    [endtime] [datetime] NULL,
    [starttime] [datetime] NULL,
    [description] [varchar](255) NULL,
    [innername] [varchar](255) NOT NULL,
    [name] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[VariableGroupMapping]    Script Date: 2015/9/14 21:43:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[VariableGroupMapping](
    [id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
    [dateadded] [datetime] NOT NULL,
    [lastmodified] [datetime] NOT NULL,
    [lastmodifiedby] [varchar](255) NOT NULL,
    [endtime] [datetime] NULL,
    [starttime] [datetime] NULL,
    [groupId] [numeric](19, 0) NULL,
    [variableId] [numeric](19, 0) NULL,
PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[VariableType]    Script Date: 2015/9/14 21:43:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[VariableType](
    [id] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
    [dateadded] [datetime] NOT NULL,
    [lastmodified] [datetime] NOT NULL,
    [lastmodifiedby] [varchar](255) NOT NULL,
    [endtime] [datetime] NULL,
    [starttime] [datetime] NULL,
    [description] [varchar](255) NULL,
    [innername] [varchar](255) NOT NULL,
    [name] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[NominalValue]  WITH CHECK ADD  CONSTRAINT [FK_f4nywwje73bs869w8glonbodq] FOREIGN KEY([nominalTypeId])
REFERENCES [dbo].[NominalType] ([id])
GO
ALTER TABLE [dbo].[NominalValue] CHECK CONSTRAINT [FK_f4nywwje73bs869w8glonbodq]
GO
ALTER TABLE [dbo].[Variable]  WITH CHECK ADD  CONSTRAINT [FK_8n9go1t40andxvfosngmalkn9] FOREIGN KEY([nominalTypeId])
REFERENCES [dbo].[NominalType] ([id])
GO
ALTER TABLE [dbo].[Variable] CHECK CONSTRAINT [FK_8n9go1t40andxvfosngmalkn9]
GO
ALTER TABLE [dbo].[Variable]  WITH CHECK ADD  CONSTRAINT [FK_cfea3aryi6a5nusvaegu52rpg] FOREIGN KEY([typeId])
REFERENCES [dbo].[VariableType] ([id])
GO
ALTER TABLE [dbo].[Variable] CHECK CONSTRAINT [FK_cfea3aryi6a5nusvaegu52rpg]
GO
ALTER TABLE [dbo].[VariableGroupMapping]  WITH CHECK ADD  CONSTRAINT [FK_cwkn583t6yfyqsffeevwp9r3f] FOREIGN KEY([groupId])
REFERENCES [dbo].[VariableGroup] ([id])
GO
ALTER TABLE [dbo].[VariableGroupMapping] CHECK CONSTRAINT [FK_cwkn583t6yfyqsffeevwp9r3f]
GO
ALTER TABLE [dbo].[VariableGroupMapping]  WITH CHECK ADD  CONSTRAINT [FK_d0ded3470sovs0m0f5mn5ompu] FOREIGN KEY([variableId])
REFERENCES [dbo].[Variable] ([id])
GO
ALTER TABLE [dbo].[VariableGroupMapping] CHECK CONSTRAINT [FK_d0ded3470sovs0m0f5mn5ompu]
GO
