import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {DonorFormComponent} from './features/auth/donor-form/donor-form.component';
import {LoginComponent} from './features/auth/login/login.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'econexo';
}
