USE [metadata]
GO

delete from [dbo].[VariableType] where name = 'String' or name = 'Boolean' or name = 'Integer' or name = 'Float' or name = 'Double' or name = 'Decimal' or name = 'Date' or name = 'SingleNominal' or name = 'MultiNominal' 
INSERT INTO [dbo].[VariableType] ([dateadded],[lastmodified],[lastmodifiedby],[starttime],[description],[innername],[name]) VALUES (getdate() ,getdate() ,'sys' ,getdate() ,'String','String','String')
INSERT INTO [dbo].[VariableType] ([dateadded],[lastmodified],[lastmodifiedby],[starttime],[description],[innername],[name]) VALUES (getdate() ,getdate() ,'sys' ,getdate() ,'Boolean','Boolean','Boolean')
INSERT INTO [dbo].[VariableType] ([dateadded],[lastmodified],[lastmodifiedby],[starttime],[description],[innername],[name]) VALUES (getdate() ,getdate() ,'sys' ,getdate() ,'Integer','Integer','Integer')
INSERT INTO [dbo].[VariableType] ([dateadded],[lastmodified],[lastmodifiedby],[starttime],[description],[innername],[name]) VALUES (getdate() ,getdate() ,'sys' ,getdate() ,'Float','Float','Float')
INSERT INTO [dbo].[VariableType] ([dateadded],[lastmodified],[lastmodifiedby],[starttime],[description],[innername],[name]) VALUES (getdate() ,getdate() ,'sys' ,getdate() ,'Double','Double','Double')
INSERT INTO [dbo].[VariableType] ([dateadded],[lastmodified],[lastmodifiedby],[starttime],[description],[innername],[name]) VALUES (getdate() ,getdate() ,'sys' ,getdate() ,'Decimal','Decimal','Decimal')
INSERT INTO [dbo].[VariableType] ([dateadded],[lastmodified],[lastmodifiedby],[starttime],[description],[innername],[name]) VALUES (getdate() ,getdate() ,'sys' ,getdate() ,'Date','Date','Date')
INSERT INTO [dbo].[VariableType] ([dateadded],[lastmodified],[lastmodifiedby],[starttime],[description],[innername],[name]) VALUES (getdate() ,getdate() ,'sys' ,getdate() ,'SingleNominal','SingleNominal','SingleNominal')
INSERT INTO [dbo].[VariableType] ([dateadded],[lastmodified],[lastmodifiedby],[starttime],[description],[innername],[name]) VALUES (getdate() ,getdate() ,'sys' ,getdate() ,'MultiNominal','MultiNominal','MultiNominal')
