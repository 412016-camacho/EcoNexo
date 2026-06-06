import {Component, inject, Input} from '@angular/core';
import {AuthService} from '../../../core/services/auth.service';
import {Router} from '@angular/router';
import {DonorResponse} from '../../models/donor.model';
import {NgoResponseDTO} from '../../models/ngo.model';
import {DriverResponse} from '../../models/driver.model';

@Component({
  selector: 'app-navbar',
  imports: [],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  private authService = inject(AuthService);
  private router = inject(Router);

  @Input() userName: DonorResponse | NgoResponseDTO | DriverResponse | string = '';

  logout() {
    this.authService.logout().subscribe({
      next: () => this.router.navigate(['/login']),
      error: (err) => {
        console.error('Error al cerrar sesión', err);
        this.router.navigate(['/login']);
      }
    });
  }
}
