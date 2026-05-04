import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { TutorListComponent } from './components/tutor-list/tutor-list.component';
import { TutorProfileComponent } from './components/tutor-profile/tutor-profile.component';
import { RequestDashboardComponent } from './components/request-dashboard/request-dashboard.component';

import { LayoutComponent } from './shared/components/layout/layout.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: 'tutors', component: TutorListComponent },
      { path: 'tutor-profile', component: TutorProfileComponent },
      { path: 'dashboard', component: RequestDashboardComponent },
      { path: 'profile', component: TutorProfileComponent },
      { path: '', redirectTo: '/tutors', pathMatch: 'full' }
    ]
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
