from flask import Flask, render_template
import os
from datetime import datetime

app = Flask(__name__)

@app.route('/')
def home():
    return render_template('index.html', 
                         title='Flask CI/CD Demo',
                         timestamp=datetime.now().strftime('%Y-%m-%d %H:%M:%S'))

@app.route('/health')
def health():
    return {'status': 'healthy', 'timestamp': datetime.now().isoformat()}

if __name__ == '__main__':
    port = int(os.environ.get('PORT', 5000))
    app.run(host='0.0.0.0', port=port, debug=True)
