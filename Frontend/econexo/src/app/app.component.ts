import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {DonorFormComponent} from './features/auth/donor-form/donor-form.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, DonorFormComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'econexo';
}
