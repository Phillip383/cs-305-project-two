import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class HashService {
  private http = inject(HttpClient);

  public hash_request(data: String) {
    let response = '';
    return this.http.post('api/hash', {'data': data}, { responseType: 'text'});
  }
}
