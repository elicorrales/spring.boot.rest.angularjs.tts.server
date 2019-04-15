#!/bin/bash
kill $(ps -ef|grep espeak|grep -v grep|awk '{print $2}' ) 2>/dev/null;
exit 0;