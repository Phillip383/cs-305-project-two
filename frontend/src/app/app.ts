import { Component, signal, inject } from '@angular/core';
import { HashService } from '../services/hash-service/hash-service';
import { FormsModule } from '@angular/forms';
import { lastValueFrom } from 'rxjs';

@Component({
  selector: 'app-root',
  imports: [FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  private hashService = inject(HashService);
  protected readonly title = signal('Project Two');
  protected data = signal('');

  async onSubmit(event: Event) {
    try {
      const response = await lastValueFrom(this.hashService.hash_request(this.data()));
      alert(response.toString());
    } catch(error) {
      alert('Request Failed: ' + error);
    }
  }
}
