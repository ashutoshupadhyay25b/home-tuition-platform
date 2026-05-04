import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { TutorListComponent } from './components/tutor-list/tutor-list.component';
import { TutorProfileComponent } from './components/tutor-profile/tutor-profile.component';
import { RequestDashboardComponent } from './components/request-dashboard/request-dashboard.component';
import { HomeComponent } from './components/home/home.component';

import { LayoutComponent } from './shared/components/layout/layout.component';

import { TutorDetailComponent } from './components/tutor-detail/tutor-detail.component';

import { AuthGuard } from './guards/auth.guard';

import { LeadListComponent } from './components/lead-list/lead-list.component';
import { PostRequirementComponent } from './components/post-requirement/post-requirement.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: 'tutors', component: TutorListComponent },
      { path: 'tutor/:id', component: TutorDetailComponent },
      { path: 'tutor-profile', component: TutorProfileComponent, canActivate: [AuthGuard] },
      { path: 'dashboard', component: RequestDashboardComponent, canActivate: [AuthGuard] },
      { path: 'post-requirement', component: PostRequirementComponent, canActivate: [AuthGuard] },
      { path: 'leads', component: LeadListComponent, canActivate: [AuthGuard] },
      { path: 'profile', component: TutorProfileComponent, canActivate: [AuthGuard] },
      { path: 'home-tuition-:city', component: TutorListComponent },
      { path: '', component: HomeComponent, pathMatch: 'full' }
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
