import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { TutorListComponent } from './components/tutor-list/tutor-list.component';
import { TutorProfileComponent } from './components/tutor-profile/tutor-profile.component';
import { RequestDashboardComponent } from './components/request-dashboard/request-dashboard.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'tutors', component: TutorListComponent },
  { path: 'tutor-profile', component: TutorProfileComponent },
  { path: 'dashboard', component: RequestDashboardComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
