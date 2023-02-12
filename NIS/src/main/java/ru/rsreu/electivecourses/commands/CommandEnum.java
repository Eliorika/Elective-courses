package ru.rsreu.electivecourses.commands;

import ru.rsreu.electivecourses.commands.admin.*;
import ru.rsreu.electivecourses.commands.common.*;
import ru.rsreu.electivecourses.commands.moderator.*;
import ru.rsreu.electivecourses.commands.student.*;
import ru.rsreu.electivecourses.commands.tutor.*;

public enum CommandEnum {

	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	LOGOUT{
		{
			this.command = new LogoutCommand();
		}
		
	},
	GOTOWELCOMENOTAUTHORIZED {
		{
			this.command = new WellcomeNotAuthorizedCommand();
		}
	},
	GOTOABOUTUSNOTAUTHORIZED {
		{
			{
				this.command = new AboutUsNotAuthorizedCommand();
			}
		}
	},
	GOTOAUTHORIZATION {
		{
			this.command = new AuthorizationCommand();
		}
	},
	GOTOWELCOMEAUTHORIZED {
		{
			this.command = new WellcomeAuthorizedCommand();
		}
	},
	GOTOINFORMATIONFC {
		{
			this.command = new InformationFCCommand();
		}
	},
	GOTOABOUTUSAUTHORIZED {
		{
			{
				this.command = new AboutUsAuthorizedCommand();
			}
		}
	},
	GOTOSTUDENTCOURSES {
		{
			{
				this.command = new StudentCoursesCommand();
			}
		}
	},
	GOTOSIGNCOURSE {
		{
			{
				this.command = new SignCourseCommand();
			}
		}
	},
	GOTOSTUDENTMARKS {
		{
			{
				this.command = new StudentMarksCommand();
			}
		}
	},
	GOTOSTUDENTATTENDANCE {
		{
			{
				this.command = new StudentAttendanceCommand();
			}
		}
	},
	GOTOSTUDENTPROFILE {
		{
			{
				this.command = new StudentProfileCommand();
			}
		}
	}, 
	GOTONEWCOURSE {
		{
			{
				this.command = new NewCourseCommand();
			}
		}
	},
	GOTOTUTORCOURSES {
		{
			{
				this.command = new TutorsCoursesCommand();
			}
		}
	},
	GOTODOCS {
		{
			{
				this.command = new DocsCommand();
			}
		}
	}, 
	GOTOFINALDOCS {
		{
			{
				this.command = new FinalDocsCommand();
			}
		}
	}, 
	GOTOTUTORPROFILE{
		{
			this.command = new TutorProfileCommand();
		}
	},
	GOTOADMINFINDUSER{
		{
			this.command = new AdminFindUserPageCommand();
		}
	},
	GOTOADMINPROFILE{
		{
			this.command = new AdminProfileCommand();
		}
	},
	GOTOCREATEUSER{
		{
			this.command = new CreateUserPageCommand();
		}
	},
	GOTOALLUSERSFORADMIN{
		{
			this.command = new AllUsersForAdminCommand();
		}
	},
	GOTOMODERATORPROFILE{
		{
			this.command = new ModeratorProfileCommand();
		}
	},
	GOTOALLUSERSMODERATOR{
		{
			this.command = new AllUsersForModeratorCommand();
		}
	},
	GOTOMODERATORFINDUSER{
		{
			this.command = new ModeratorFindUserCommand();
		}
	},
	GOTOABOUTUSOTHER{
		{
			this.command = new AboutUsOtherCommand();
		}
	},
	GOTOWELCOMEOTHER {
		{
			this.command = new WelcomeOtherCommand();
		}
	},
	CREATEUSER{
		{
			this.command = new CreateUserCommand();
		}
	},
	FINDUSER{
		{
			this.command = new FindUserCommand();
		}
	},
	DELETEUSER{
		{
			this.command = new DeleteUserCommand();
		}
	},
	BLOCKUSER{
		{
			this.command = new BlockUserCommand();
		}
	},
	UNBLOCKUSER{
		{
			this.command = new UnblockUserCommand();
		}
	},
	EDITUSER{
		{
			this.command = new EditUserCommand();
		}
	},
	GOTOPROFILE{
		{
			this.command = new ProfileCommand();
		}
	},
	CREATECOURSE {
		{
			this.command = new CreateCourseCommand();
		}
	},
	SIGNCOURSE{
		{
			this.command = new SignForCourseCommand();
		}
	},
	EXPELCOURSE{
		{
			this.command = new ExpelCourseCommand();
		}
	},
	GETMARKS{
		{
			this.command = new GetMarksCommand();
		}
	},
	GETSTUDENTSOFCOURSE{
		{
			this.command = new GetCourseStudents();
		}
	},
	GETFINALMARKS{
		{
			this.command = new GetFinalMarks();
		}
	},
	GETSTUDENTSDOCS{
		{
			this.command = new GetStudentDocs();
		}
	},
	ADDDOCS{
		{
			this.command = new AddStudentDocs();
		}
	},
	ADDFINALDOCS{
		{
			this.command = new AddFinalDocs();
		}
	},
	ERRORPAGE{
		{
			this.command = new GotoErrorPage();
		}
	};
	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}
}
