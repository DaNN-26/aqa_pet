#!/bin/bash
echo "Waiting for Selenoid to be ready..."
while ! curl -s http://localhost:4444/status | grep -q '"state":"ready"'; do
    sleep 1
done
echo "Selenoid is ready!"