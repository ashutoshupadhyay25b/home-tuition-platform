import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatDividerModule } from '@angular/material/divider';
import { MatRadioModule } from '@angular/material/radio';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSliderModule } from '@angular/material/slider';

import { TutorListComponent } from '../../components/tutor-list/tutor-list.component';
import { TutorProfileComponent } from '../../components/tutor-profile/tutor-profile.component';
import { TutorDetailComponent } from '../../components/tutor-detail/tutor-detail.component';
import { LeadListComponent } from '../../components/lead-list/lead-list.component';

@NgModule({
  declarations: [
    TutorListComponent,
    TutorProfileComponent,
    TutorDetailComponent,
    LeadListComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatCardModule,
    MatSelectModule,
    MatIconModule,
    MatChipsModule,
    MatDividerModule,
    MatRadioModule,
    MatProgressSpinnerModule,
    MatPaginatorModule,
    MatSliderModule
  ]
})
export class TutorModule { }
